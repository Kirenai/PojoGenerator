import { BiArrowBack } from 'react-icons/bi'
import { FaJava } from 'react-icons/fa6'
import { Link, Outlet, useLocation, useNavigate } from 'react-router-dom'
import {
  Box,
  Container,
  HStack,
  Icon,
  IconButton,
  Text,
} from '@chakra-ui/react'
import { ColorModeButton } from './color-mode'

type BackIconProps = {
  pathname: string
  handleOnClick: () => void
}

const BackIconButton = ({ pathname, handleOnClick }: BackIconProps) => {
  if (pathname !== '/') {
    return (
      <IconButton
        variant="surface"
        colorPalette="teal"
        data-state="open"
        size="sm"
        _open={{
          animationName: 'fade-in, scale-in',
          animationDuration: '300ms',
        }}
        onClick={handleOnClick}
      >
        <BiArrowBack />
      </IconButton>
    )
  }
}

const Navbar = () => {
  const { pathname } = useLocation()
  const navigate = useNavigate()

  const handleOnClick = () => {
    navigate(-1)
  }

  return (
    <>
      <Container mt="2">
        <Box position="relative" w="full" textAlign="start">
          <Icon fontSize="4xl">
            <FaJava />
          </Icon>
          <HStack gap="4" position="absolute" top="0" right="0">
            <BackIconButton pathname={pathname} handleOnClick={handleOnClick} />
            <Link to="/pojo">
              <Text fontSize="sm" fontWeight="medium">
                Generator
              </Text>
            </Link>
            <ColorModeButton />
          </HStack>
        </Box>
      </Container>
      <Outlet />
    </>
  )
}

export default Navbar
