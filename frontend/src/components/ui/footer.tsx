import { Box, Container, HStack, Text, VStack } from '@chakra-ui/react'

const Footer = () => {
  return (
    <Container py="8" flex="1" alignContent="end">
      <Box as="footer" fontSize={{ base: 'sm' }}>
        <HStack>
          <VStack alignItems="flex-start" gap="2">
            <Text color="gray.400">Proyecto realizado por Kirenai © 2024</Text>
            <Text color="gray.400">Mantenido por Kirenai</Text>
          </VStack>
        </HStack>
      </Box>
    </Container>
  )
}

export default Footer
